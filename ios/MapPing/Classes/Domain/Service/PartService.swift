//
//  PartService.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation

class PartService {
    private let partsUrl = URL(string: "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json")!

    var partsObservable = Observable<[Part]>()

    func refreshParts() {
        partsObservable.notify(data: [])
        // TODO 🙄
    }
}
