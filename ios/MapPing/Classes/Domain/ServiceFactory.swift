//
//  ServiceFactory.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ServiceFactory {
    private let uniquePartService = PartService()

    func partService() -> PartService {
        return uniquePartService
    }
}
