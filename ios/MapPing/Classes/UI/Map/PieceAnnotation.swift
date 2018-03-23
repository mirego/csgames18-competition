//
//  PieceAnnotation.swift
//  MapPing
//
//  Created by Hugo Lefrancois on 2018-03-22.
//  Copyright © 2018 Mirego. All rights reserved.
//

import MapKit

class PieceAnnotation: NSObject, MKAnnotation {
    let coordinate: CLLocationCoordinate2D
    let iconName: String

    init(coordinate: CLLocationCoordinate2D, iconName: String) {
        self.coordinate = coordinate
        self.iconName = iconName
    }
}
