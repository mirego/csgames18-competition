//
//  ListView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListView: UIView {

    private let partCellView = PartCellView()

    init() {
        super.init(frame: .zero)
        backgroundColor = .white

        partCellView.configure(partImageName: "part-sensor", title: "Bougie 4W", subTitle: "Moteur principal", coordinates: "46.7552° N, 71.2265° W", distance: "(0.62 km)")
        
        addSubview(partCellView)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        partCellView.pin.top().horizontally()
    }
}
