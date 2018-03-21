//
//  ViewControllerFactory.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ViewControllerFactory {
    func rootViewController() -> UIViewController {
        return assign(RootViewController(viewControllers: [mapViewController(), listViewController(), augmentedRealityViewController()]))
    }

    func mapViewController() -> BaseViewController {
        return assign(MapViewController())
    }

    func listViewController() -> BaseViewController {
        return assign(ListViewController())
    }

    func augmentedRealityViewController() -> BaseViewController {
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
