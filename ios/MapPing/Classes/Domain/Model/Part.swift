//
//  Part.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import CoreLocation

struct Part: Codable, Equatable {
    enum CodingKeys: String, CodingKey {
        case name
        case latitude = "lat"
        case longitude = "lon"
        case component
        case type
        case address
        case notes
    }

    let name: String
    let latitude: Float?
    let longitude: Float?
    let component: String
    let type: String
    let address: String?
    let notes: String
    
    public static func ==(lhs: Part, rhs: Part) -> Bool {
        return lhs.name == rhs.name &&
                lhs.latitude == rhs.latitude &&
                lhs.longitude == rhs.longitude &&
                lhs.component == rhs.component &&
                lhs.type == rhs.type &&
                lhs.address == rhs.address &&
                lhs.notes == rhs.notes
    }
}
