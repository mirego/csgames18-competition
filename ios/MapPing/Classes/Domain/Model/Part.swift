//
//  Part.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

struct Part: Codable {
    enum CodingKeys: String, CodingKey {
        case name
        case latitude = "lat"
        case longitude = "lon"
        case type
        case notes
        case address
        case component
    }

    let name: String
    let latitude: Float
    let longitude: Float
    let type: String
    let notes: String
    let address: String
    let component: String
    var formattedCoordinates : String {
        let formattedLat = "\(abs(latitude))° \(latitude < 0 ? "S" : "N")"
        let formattedLong = "\(abs(longitude))° \(longitude < 0 ? "W" : "E")"
        return "\(formattedLat), \(formattedLong)"
    }
}
