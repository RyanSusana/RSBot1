package org.persuasive.api.script.wrappers;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.DepositBox;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.node.Item;

public class AEBank {

	private Area b = null;
	private boolean isDeposit = false;

	public AEBank(Area n, boolean depositBox) {
		b = n;
		this.isDeposit = depositBox;
	}
	public Area getArea(){
		return b;
	}
	public boolean atBank() {
		if (getNearest() != null && getNearest().isOnScreen()) {
			return true;
		}
		return b.contains(Players.getLocal().getLocation());
	}

	public boolean close() {
		return isDepositBank() ? DepositBox.close() : Bank.close();
	}

	public boolean isDepositBank() {
		return isDeposit;
	}

	public Entity getNearest() {
		return isDepositBank() ? DepositBox.getNearest() : Bank.getNearest();
	}

	public boolean isOpen() {
		return isDepositBank() ? DepositBox.isOpen() : Bank.isOpen();
	}

	public boolean open() {
		return isDepositBank() ? DepositBox.open() : Bank.open();
	}

	public static boolean depositAllExcept(final int... itemIDs) {
		for (Integer i : itemIDs) {
			if (i == null) {
				return false;
			}
		}
		if (Bank.isOpen()) {
			if (Inventory.getCount(true) - Inventory.getCount(true, itemIDs) <= 0) {
				return true;
			}
			if (Inventory.getCount() == 0) {
				return true;
			}
			if (Inventory.getCount(true, itemIDs) == 0) {
				return Bank.depositInventory();
			}
			outer: for (final Item item : Inventory.getItems()) {
				if (item != null && item.getId() != -1) {
					for (final int itemID : itemIDs) {
						if (item.getId() == itemID) {
							continue outer;
						}
					}
					for (int j = 0; j < 5
							&& Inventory.getCount(item.getId()) != 0; j++) {
						if (Bank.deposit(item.getId(), 0)) {
							Task.sleep(40, 120);
						}
					}
				}
			}
			return Inventory.getCount(true) - Inventory.getCount(true, itemIDs) <= 0;
		} else if (DepositBox.isOpen()) {
			if (DepositBox.getItemCount(true)
					- DepositBox.getItemCount(true, itemIDs) <= 0) {
				return true;
			}
			if (DepositBox.getItemCount() == 0) {
				return true;
			}
			if (DepositBox.getItemCount(true, itemIDs) == 0) {
				return DepositBox.depositInventory();
			}
			outer: for (final Item item : DepositBox.getItems()) {
				if (item != null && item.getId() != -1) {
					for (final int itemID : itemIDs) {
						if (item.getId() == itemID) {
							continue outer;
						}
					}
					for (int j = 0; j < 5
							&& DepositBox.getItemCount(item.getId()) != 0; j++) {
						if (DepositBox.deposit(item.getId(), 0)) {
							Task.sleep(40, 120);
						}
					}
				}
			}
			return DepositBox.getItemCount(true)
					- DepositBox.getItemCount(true, itemIDs) <= 0;
		}
		return false;
	}

}
