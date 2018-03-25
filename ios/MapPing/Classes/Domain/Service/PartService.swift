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
        let sessionConfig = URLSessionConfiguration.default
        let session = URLSession(configuration: sessionConfig)
        
        session.dataTask(with: partsUrl, completionHandler: { (data, response, error) in
            guard let data = data else { print("Error!!"); return }
            
            do {
                let parts = try JSONDecoder().decode([Part].self, from: data)
                DispatchQueue.main.async {
                    self.partsObservable.notify(data: parts)
                }
            } catch {
                print("Error while deserializing: \(error)")
            }
        }).resume()
    }
}
