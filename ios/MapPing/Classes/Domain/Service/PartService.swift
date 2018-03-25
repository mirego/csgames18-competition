//
//  PartService.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation
import CodableAlamofire
import Alamofire
import Dispatch
import CoreLocation

class PartService {
    private let partsUrl = URL(string: "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json")!

    var partsObservable = Observable<[Part]>()

    func refreshParts() {
        Alamofire.request(partsUrl).responseDecodableObject { (response: DataResponse<[Part]>) in
            guard let parts = response.result.value else {
                print("Error querrying the parts url", response.error!)
                return
            }
            
//            let dispatchGroup = DispatchGroup()
            
            var finalParts = [Part]()
            for part in parts {
//                dispatchGroup.enter()
                
                if part.address == nil {
//                    let location = CLLocation(latitude: Double(part.latitude!), longitude: Double(part.longitude!))
//                    LocationService.instance.getAddressFromLocation(location: location, completion: { (addr) in
//                        let p = Part(name: part.name,
//                                     latitude: part.latitude,
//                                     longitude: part.longitude,
//                                     component: part.component,
//                                     type: part.type,
//                                     address: addr,
//                                     notes: part.notes)
//                        finalParts.append(p)
////                        dispatchGroup.leave()
//                    })
                } else if part.latitude == nil {
//                    LocationService.instance.getLocationFromAddress(address: part.address!, completion: { (location) in
//                        let p = Part(name: part.name,
//                                     latitude: Float(location.coordinate.latitude),
//                                     longitude: Float(location.coordinate.longitude),
//                                     component: part.component,
//                                     type: part.type,
//                                     address: part.address,
//                                     notes: part.notes)
//                        finalParts.append(p)
////                        dispatchGroup.leave()
//                    })
                } else {
                    finalParts.append(part)
//                    dispatchGroup.leave()
                }
            }
            
//            dispatchGroup.wait()
            self.partsObservable.notify(data: finalParts)
//            dispatchGroup.notify(queue: .main) {
//                self.partsObservable.notify(data: parts)
//            }
        }
        partsObservable.notify(data: [])
        // TODO 🙄
    }
}
