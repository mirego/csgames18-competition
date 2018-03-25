//
//  ListView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit


class ListView: UIView {

    private let partCellView = PartCellView()
    private let secondPart = PartCellView()
    
    private let cellView = [newPartCellView()]
    
    

    init() {
        super.init(frame: .zero)
        backgroundColor = .white

        
        partCellView.configure(partImageName: "part-sensor", title: "Bougie 4W", subTitle: "Moteur principal", coordinates: "46.7552° N, 71.2265° W", distance: "(0.62 km)")
        addSubview(partCellView)
        secondPart.configure(partImageName: "part-sensor", title: "Bougie 4W", subTitle: "Moteur principal", coordinates: "46.7552° N, 71.2265° W", distance: "(0.62 km)")
        secondPart.layoutSubviews()
        addSubview(secondPart)

        self.decodeJSON()
    }
    
    
    
  

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        partCellView.pin.top().horizontally()
        secondPart.pin.below(of: partCellView)
        
    }
    
    
    func decodeJSON() {
        struct mutipleLocations: Codable {
            var locations: [Location]
        }
        
        struct Location: Codable {
            let name: String
            let component: String
            let notes: String
            let type: String
            let lat: Float?
            let lon: Float?
            let address: String?
        }
        
        
        
        let json = """
    {[{
     "name": "GÃ©nÃ©rateur de flammÃ¨ches",
     "component": "Propulsion intergalactique",
     "notes": "Passer au Bureau 200",
     "type": "generator",
     "lat": 46.7909223,
     "lon": -71.2873006,
     "address": "2327 Boulevard du Versant N, Ville de QuÃ©bec, QC G1N 4C2, Canada"
     },  "name": "GÃ©nÃ©rateur de flammÃ¨ches",
     "component": "Propulsion intergalactique",
     "notes": "Passer au Bureau 200",
     "type": "generator",
     "lat": 46.7909223,
     "lon": -71.2873006,
     "address": "2327 Boulevard du Versant N, Ville de QuÃ©bec, QC G1N 4C2, Canada"
     }
    ]}
    """.data(using: .utf8)!
        
        
        
        
        let decoder = JSONDecoder()
        do {
        let product = try decoder.decode(Location.self, from: json)
             print(product.name)
            print(product.component)
        } catch {
            print("an error occurred")
        }
    }
    
    
}
