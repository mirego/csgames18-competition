//
//  ViewControllerFactory.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ViewControllerFactory {
    private let serviceFactory: ServiceFactory

    init(serviceFactory: ServiceFactory) {
        self.serviceFactory = serviceFactory
    }

    func rootViewController() -> UIViewController {
        return assign(RootViewController(viewControllers: [listViewController(), mapViewController(), augmentedRealityViewController()]))
    }

    private func mapViewController() -> MapViewController {
        return assign(MapViewController())
    }

    private func listViewController() -> NavigationViewController {
        let listViewController = assign(ListViewController(partService: serviceFactory.partService()))
        return NavigationViewController(rootViewController: listViewController)
    }

    private func augmentedRealityViewController() -> AugmentedRealityViewController {
        return assign(AugmentedRealityViewController())
    }
}

// MARK: Helpers
extension ViewControllerFactory {
    fileprivate func assign<T: BaseViewController>(_ viewController: T) -> T {
        viewController.viewControllerFactory = self
        return viewController
    }
}
