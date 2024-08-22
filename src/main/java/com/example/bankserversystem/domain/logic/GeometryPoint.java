package com.example.bankserversystem.domain.logic;


import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryPoint {
    public static Point createPoint(Double latitude, Double longitude) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),4326);
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }
}
