//
//  PartService.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation
import CodableAlamofire
import Alamofire

class PartService {
    private let partsUrl = URL(string: "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json")!

    var partsObservable = Observable<[Part]>()

    func refreshParts() {
        Alamofire.request(partsUrl).responseDecodableObject { (response: DataResponse<[Part]>) in
            guard let parts = response.result.value else {
                print("Error querrying the parts url", response.error!)
                return
            }
            self.partsObservable.notify(data: parts)
        }
        partsObservable.notify(data: [])
        // TODO 🙄
    }
}
