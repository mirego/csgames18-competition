//
//  MapViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import MapKit

class MapViewController: BaseViewController {
    private let quebecCityCoordinate = CLLocationCoordinate2D(latitude: 46.780904, longitude: -71.277222)
    private let startSpan = MKCoordinateSpan(latitudeDelta: 0.3, longitudeDelta: 0.3)

    private var mainView: MapView {
        return self.view as! MapView
    }
    
    private let partService: PartService

    init(partService: PartService) {
        self.partService = partService
        super.init(nibName: nil, bundle: nil)
        navigationIcon = #imageLiteral(resourceName: "icn-map")
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = MapView()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        mainView.mapView.setRegion(MKCoordinateRegion(center: quebecCityCoordinate, span: startSpan), animated: false)
        
        _ = partService.partsObservable.register { [weak self] (_, parts) in
            print("Nb of parts received: \(parts.count)")
            self?.render(parts)
        }
    }
    
    private func render(_ parts: [Part]) {
        for part in parts {
            guard let lat = part.latitude, let long = part.longitude else { continue }
            
            mainView.mapView.addAnnotation(
                PartAnnotation(
                    coordinate: CLLocationCoordinate2D(latitude: Double(lat), longitude: Double(long)),
                    iconName: "part-\(part.type)"
                )
            )
        }
        
    }
}
