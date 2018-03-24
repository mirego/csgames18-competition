//
//  PartService.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class PartService {
    let partsUrl = "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json"

    var partsObservable = Observable<[Part]>()

    func refreshParts() {
        HttpService.get(url: partsUrl) { (data, error) in
            guard let data = data else {
                return
            }
            do {
                let partsData = try JSONDecoder()
                    .decode([FailableDecodable<Part>].self, from: data)
                    .flatMap { $0.base }
                
                self.partsObservable.notify(data:partsData)
            } catch {
                print("Error trying to convert data to JSON")
            }
        }
    }
    
    static func getParts(completion: @escaping (_ posts: [Part]?, _ error: Error?) -> ()) {
        
    }
}
