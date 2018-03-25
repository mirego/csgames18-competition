//
//  ListView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListView: UITableView {
    
    private let partCellView = PartCellView()

    private let reuse = "item"
    
    var itemData:Array<NSDictionary>?
    
    init() {
        super.init(frame: .zero, style: .plain)
        backgroundColor = .white
        
        self.delegate = self
        self.dataSource = self
        self.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.register(PartCellView.self, forCellReuseIdentifier: reuse)

        // Use the URLService to retrieve the part data.
        URLService.getData(){ json in
            self.itemData = json
            self.reloadData()
        }
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        partCellView.pin.top().horizontally()
    }
    
    override func numberOfRows(inSection section: Int) -> Int {
        return (itemData == nil) ? 0 : itemData!.count
    }
    
}

extension ListView : UITableViewDelegate, UITableViewDataSource {
    
    /*
     * Specify the table cell height.
     */
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100.0
    }
    
    /*
     * Get number of rows, which corresponds the the number of
     * items in the itemData. If itemData is nil, 0 is returned.
     */
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (itemData == nil) ? 0 : itemData!.count
    }
    
    /*
     * Handle reusing table cells. The PartCellView cells are updated
     * with the data the corresponds to the matching index in itemData.
     */
    internal func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: reuse, for: indexPath as IndexPath)
        if let part = cell as? PartCellView {
            if let itemData = itemData {
                part.configure(dict: itemData[indexPath.row])
            }
        }
        return cell
    }

}
