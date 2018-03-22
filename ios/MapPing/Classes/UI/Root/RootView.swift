//
//  RootView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import PinLayout

protocol RootViewDelegate: class {
    func didTapButton(at index: Int)
}

class RootView: UIView {
    weak var delegate: RootViewDelegate?

    private var displayedView: UIView
    private let navigationButtons: NavigationButtons

    init(view: UIView, icons: [UIImage]) {
        displayedView = view
        navigationButtons = NavigationButtons(images: icons)
        super.init(frame: .zero)
        backgroundColor = .orange

        addSubview(displayedView)
        navigationButtons.delegate = self
        addSubview(navigationButtons)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        displayedView.pin.all()

        let sideMargin: CGFloat = 20
        navigationButtons.pin.bottom(safeAreaInsets.bottom + sideMargin).right(sideMargin)
    }

    func setDisplayedView(_ view: UIView, animated: Bool, completion: @escaping () -> ()) {
        view.alpha = 0
        insertSubview(view, belowSubview: navigationButtons)
        view.pin.all()
        UIView.animate(withDuration: 0.4, animations: {
            view.alpha = 1
        }) { [weak self] (_) in
            self?.displayedView.removeFromSuperview()
            self?.displayedView = view
            completion()
        }
    }

    func setSelectedButton(index: Int) {
        navigationButtons.setSelectedButton(index: index)
    }
}

extension RootView: NavigationButtonsDelegate {
    func didTapButton(at index: Int) {
        delegate?.didTapButton(at: index)
    }
}
