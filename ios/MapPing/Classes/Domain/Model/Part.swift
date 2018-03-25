//
//  Part.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

struct Part: Codable {
    enum CodingKeys: String, CodingKey {
        case name
        case component
        case notes
        case type
        case latitude = "lat"
        case longitude = "lon"
        case address
    }

    let name: String
    let component: String
    let notes: String
    let type: PartType
    let latitude: Float
    let longitude: Float
    let address: String
}
