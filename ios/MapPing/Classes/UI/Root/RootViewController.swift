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

    private let viewControllers: [UIViewController & NavigationIconProvider]
    private var displayedViewController: Int = 1
    private var transitionInProgress = false

    init(viewControllers: [UIViewController & NavigationIconProvider]) {
        assert(!viewControllers.isEmpty, "RootViewController needs at least one view controller")
        self.viewControllers = viewControllers
        super.init(nibName: nil, bundle: nil)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override var childViewControllerForStatusBarStyle: UIViewController? {
        return viewControllers[displayedViewController]
    }

    override var childViewControllerForStatusBarHidden: UIViewController? {
        return viewControllers[displayedViewController]
    }

    override func loadView() {
        let firstViewController = viewControllers[displayedViewController]
        addChildViewController(firstViewController)
        view = RootView(view: firstViewController.view, icons: viewControllers.map { $0.navigationIcon ?? UIImage() })
        firstViewController.didMove(toParentViewController: self)
        mainView.delegate = self
        mainView.setSelectedButton(index: displayedViewController)
        
        // Initiate location service
        LocationService.instance.initiateLocationService()
    }
}

extension RootViewController: RootViewDelegate {
    func didTapButton(at index: Int) {
        guard displayedViewController != index && !transitionInProgress else { return }

        transitionInProgress = true
        let viewControllerToRemove = viewControllers[displayedViewController]
        let viewControllerToAdd = viewControllers[index]
        viewControllerToRemove.willMove(toParentViewController: nil)
        addChildViewController(viewControllerToAdd)
        mainView.setDisplayedView(viewControllers[index].view, animated: true) { [weak self] in
            guard let strongSelf = self else { return }
            viewControllerToAdd.didMove(toParentViewController: strongSelf)
            viewControllerToRemove.removeFromParentViewController()
            strongSelf.transitionInProgress = false
        }
        displayedViewController = index
        setNeedsStatusBarAppearanceUpdate()
        mainView.setSelectedButton(index: displayedViewController)
    }
}
