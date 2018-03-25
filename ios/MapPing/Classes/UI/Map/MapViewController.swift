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

    init() {
        super.init(nibName: nil, bundle: nil)
        navigationIcon = #imageLiteral(resourceName: "icn-map")
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }


    override func viewDidLoad() {
        super.viewDidLoad()
        mainView.mapView.setRegion(MKCoordinateRegion(center: quebecCityCoordinate, span: startSpan), animated: false)
        mainView.mapView.addAnnotation(PartAnnotation(coordinate: quebecCityCoordinate, iconName: "part-clutch"))
    }
}
