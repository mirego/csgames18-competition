//
//  ListView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListView: UIView {
    
    private var parts: [Part] = []
    private let partCellView = PartCellView()
    
    fileprivate lazy var collectionViewLayout: UICollectionViewFlowLayout = {
        let layout = UICollectionViewFlowLayout()
        layout.sectionInset = UIEdgeInsets.zero
        layout.minimumInteritemSpacing = 0
        layout.minimumLineSpacing = 0
        return layout
    }()
    
    fileprivate lazy var collectionView: UICollectionView = {
        let collectionView = UICollectionView(frame: CGRect(x: 0, y: 0, width: 0, height: 0), collectionViewLayout: self.collectionViewLayout)
        collectionView.backgroundColor = UIColor.clear
        collectionView.delegate = self
        collectionView.dataSource = self
        return collectionView
    }()
    
    let cellMargin: CGFloat = 1
    var cellDimensions: CGSize {
        return CGSize(width: UIScreen.main.bounds.width, height: 100)
    }

    init() {
        super.init(frame: .zero)
        backgroundColor = .white
        
        collectionView.register(PartCellView.self, forCellWithReuseIdentifier: PartCellView.reuseIdentifier)
        addSubview(collectionView)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        
        collectionView.frame.origin.x = 0 - cellMargin
        collectionView.frame.size.width = self.frame.size.width + cellMargin*2
        collectionView.frame.size.height = self.frame.size.height
        collectionView.frame.origin.y = 0
    }
    
    func configure(parts: [Part]) {
        self.parts = parts
        collectionView.reloadData()
    }
}

extension ListView: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return parts.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: PartCellView.reuseIdentifier, for: indexPath) as! PartCellView
        cell.configure(part: parts[indexPath.row])
        return cell
    }
}

extension ListView: UICollectionViewDelegateFlowLayout, UICollectionViewDelegate
{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return cellDimensions
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return cellMargin*2 + 1.5
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return cellMargin*2
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsetsMake(cellMargin, cellMargin, cellMargin, cellMargin) // margin between cells
    }
}
