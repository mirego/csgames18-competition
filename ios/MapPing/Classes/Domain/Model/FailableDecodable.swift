//
//  FailableDecodable.swift
//  MapPing
//
//  Created by Quentin Nolan on 18-03-24.
//  Copyright © 2018 Mirego. All rights reserved.
//

struct FailableDecodable<Base : Decodable> : Decodable {
    
    let base: Base?
    
    init(from decoder: Decoder) throws {
        let container = try decoder.singleValueContainer()
        self.base = try? container.decode(Base.self)
    }
}
