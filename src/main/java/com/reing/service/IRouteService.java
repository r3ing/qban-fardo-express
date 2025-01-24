package com.reing.service;

import com.reing.model.entity.Route;

import java.util.List;
import java.util.Map;

public interface IRouteService {

    List<Route> getAll();

    Map<String, List<String>> getRouteRolMappings();
}
