package com.company.apigateway.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtAuthConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter=new JwtGrantedAuthoritiesConverter();
    private final JwtAuthConverterProperties jwtAuthConverterProperties;



    private String getPrincipalClaimName(Jwt jwt){
        String claimName = JwtClaimNames.SUB;
        if(jwtAuthConverterProperties.getPrincipalAttribute() != null){
            claimName = jwtAuthConverterProperties.getPrincipalAttribute();
        }

        return jwt.getClaim(claimName);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt){
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if(resourceAccess == null
                || (resource = (Map<String, Object>) resourceAccess.get(jwtAuthConverterProperties.getResourceId())) == null
                || (resourceRoles = (Collection<String>) resource.get("roles")) == null ){
            return Set.of();
        }

        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

    }

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt source) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(source).stream(),
                extractResourceRoles(source).stream()).collect(Collectors.toSet());
        JwtAuthenticationToken jwtAuthenticationToken= new JwtAuthenticationToken(source, authorities, getPrincipalClaimName(source));
        return Mono.just(jwtAuthenticationToken);
    }
}
