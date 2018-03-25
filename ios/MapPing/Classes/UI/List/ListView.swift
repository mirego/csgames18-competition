//
//  ListView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListView: UIView {

    private let partCellView = PartCellView()
    private let pcv2 = PartCellView()
    
    private var pcvArr = [PartCellView]()
    private var numCells = 0
    
    var contentSizeDelegate: ContentSizeUpdate?

    init() {
        super.init(frame: .zero)
        backgroundColor = .white

        /*partCellView.configure(partImageName: "part-sensor", title: "Bougie 4W", subTitle: "Moteur principal", coordinates: "46.7552° N, 71.2265° W", distance: "(0.62 km)")
        addSubview(partCellView)
        
        
        pcv2.configure(partImageName: "part-sensor", title: "test", subTitle: "you", coordinates: "40.005 N, 71.999 W", distance: "(0.89w74km)")
        addSubview(pcv2)
        
        for number in 1...35 {
            let tempPCV = PartCellView()
            tempPCV.configure(partImageName: "part-sensor", title: "\(number)", subTitle: "tester", coordinates: "\(number) N \(number) W", distance: "none")
            
            pcvArr.append(tempPCV)
            addSubview(tempPCV)
        }*/
        
    
        
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func addPartCells(partInfo: [Part]) {
        for part in partInfo {
            let tempPCV = PartCellView()
            tempPCV.configure(partImageName: "part-\(part.type)", title: part.name, subTitle: part.description, coordinates: "\(part.latitude!),\(part.longitude!)", distance: "")
            pcvArr.append(tempPCV)
            addSubview(tempPCV)
            numCells += 1
        }
        
    }

    override func layoutSubviews() {
        super.layoutSubviews()
    
        for index in 0...pcvArr.count-1 {
            if(index > 0) {
                pcvArr[index].pin.below(of: pcvArr[index-1]).horizontally()
            } else {
                pcvArr[0].pin.top().horizontally()
            }
           
        }
        print("updating content size: \(CGFloat(numCells)*pcvArr[1].height)")
        print("arr1 height: \(pcvArr[1].height)")
        self.contentSizeDelegate!.updateContentSize(height: CGFloat(numCells)*pcvArr[1].height)
    }
    
}

protocol ContentSizeUpdate {
    func updateContentSize(height: CGFloat)
}
