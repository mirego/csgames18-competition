//
//  ListViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListViewController: BaseViewController {

    private let tableView = UITableView(frame: CGRect.zero, style: .plain)
    private let functionalTableData = FunctionalTableData()

    private let partService: PartService

    init(partService: PartService) {
        self.partService = partService
        super.init(nibName: nil, bundle: nil)
        title = "Map Ping"
        navigationIcon = #imageLiteral(resourceName: "icn-list")
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.addSubviewsForAutolayout(tableView)
        tableView.constrainToFillView(view)
    
        functionalTableData.tableView = tableView

        _ = partService.partsObservable.register { [weak self] (_, parts) in
            print("Nb of parts received: \(parts.count)")
            self?.render(parts)
        }
    }
    
    private func render(_ parts: [Part]) {
        
        var rows: [CellConfigType] = []
        for part in parts {
            rows.append(
                PartCell(
                    key: "\(part.name)",
                    style: CellStyle(
                        bottomSeparator: .inset,
                        separatorColor: .lightGray,
                        accessoryType: .disclosureIndicator
                    ),
                    actions: CellActions( selectionAction: { [weak self, part] _ in
                        guard let strongSelf = self else { return .deselected }
                        let controller = DetailsViewController(part: part)
                        strongSelf.show(controller, sender: strongSelf)
                        return .deselected
                    }),
                    state: PartCellViewState(part: part),
                    cellUpdater: PartCellViewState.updateView
                )
            )
        }
        
        let section = TableSection(
            key: "parts",
            rows: rows
        )
        
        functionalTableData.renderAndDiff([section])
    }
}
