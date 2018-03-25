//
//  ListView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListView: UIView {

    //private let partCellView = PartCellView()
    
    var currentHeight = 0.0

    init() {
        super.init(frame: .zero)
        backgroundColor = .white
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        //partCellView.pin.top().horizontally()
    }
    
    public func insert(partImageName: String, title: String, subTitle: String, coordinates: String, distance: String) {
        
        let partCellView = PartCellView()
        
        // Layout new cells below eachother
        partCellView.configure(partImageName: partImageName, title: title, subTitle: subTitle, coordinates: coordinates, distance: distance)
        
        // Place the cell manually
        if currentHeight == 0.0 {
            
            partCellView.pin.top().horizontally()
            currentHeight = Double( partCellView.bounds.height + 50 )
        
        } else {
            partCellView.frame.origin.y += CGFloat(currentHeight)
            currentHeight += 100.0
        }
        
        addSubview(partCellView)
    }
}
