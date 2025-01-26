package com.reing.service.impl;

import com.reing.model.entity.Rol;
import com.reing.model.entity.Route;
import com.reing.repository.RouteRepository;
import com.reing.service.IRouteService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RouteService implements IRouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Route> getAll() {
        return Collections.EMPTY_LIST;
        //return (List<Route>) routeRepository.findAll();
    }

    @Override
    public Map<String, List<String>> getRouteRolMappings() {
        Map<String, List<String>> map = new HashMap<>();

        List<Route> routes = getAll();

        for (Route route: routes) {
            if (!route.getRoles().isEmpty()) {
               List<String> roles = route.getRoles().stream()
                       .map(Rol::getName)
                       .collect(Collectors.toList());
               map.put(route.getRouteUrl(), roles);
            }
        }

        return map;
    }
}
