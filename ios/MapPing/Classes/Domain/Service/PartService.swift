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
        var arrayofparts = [Part]()
        // TODO 🙄
        /*do {
            let url = try Data(contentsOf: partsUrl)
            let boardsDictionary = try JSONSerialization.jsonObject(with: url, options:JSONSerialization.ReadingOptions.mutableContainers) as? Array<Dictionary<String, Any>>
            for dict in boardsDictionary! {
                let p = Part(name: dict["name"]! as! String, latitude: Float(dict["lat"]!), longitude: Float(dict["lon"]! as! String)!)
                arrayofparts.append(p)
            }
        } catch  {
           
        }*/
        

        
        do {
            let data = try Data(contentsOf: partsUrl)
            let parts = try JSONDecoder().decode(Part.self, from: data)
            print(parts)
        } catch {
            print("something was not successful")
        }
        partsObservable.notify(data: [])
//        var err: NSError?
//
//        print("1123123123 \(boardsDictionary)");
    }
}
