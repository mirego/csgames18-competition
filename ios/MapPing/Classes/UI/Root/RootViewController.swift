//
//  RootViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class RootViewController: BaseViewController {

    private var mainView: RootView {
        return self.view as! RootView
    }

    private let viewControllers: [BaseViewController]
    private var displatedViewController: Int = 1
    private var transitionInProgress = false

    init(viewControllers: [BaseViewController]) {
        assert(!viewControllers.isEmpty, "RootViewController needs at least one view controller")
        self.viewControllers = viewControllers
        super.init(nibName: nil, bundle: nil)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        let firstViewController = viewControllers[displatedViewController]
        addChildViewController(firstViewController)
        view = RootView(view: firstViewController.view, icons: viewControllers.map { $0.navigationIcon ?? UIImage() })
        firstViewController.didMove(toParentViewController: self)
        mainView.delegate = self
    }
}

extension RootViewController: RootViewDelegate {
    func didTapButton(at index: Int) {
        guard displatedViewController != index && !transitionInProgress else { return }

        transitionInProgress = true
        let viewControllerToRemove = viewControllers[displatedViewController]
        let viewControllerToAdd = viewControllers[index]
        viewControllerToRemove.willMove(toParentViewController: nil)
        addChildViewController(viewControllerToAdd)
        mainView.setDisplayedView(viewControllers[index].view, animated: true) { [weak self] in
            guard let strongSelf = self else { return }
            viewControllerToAdd.didMove(toParentViewController: strongSelf)
            viewControllerToRemove.removeFromParentViewController()
            strongSelf.transitionInProgress = false
        }
        displatedViewController = index
    }
}
