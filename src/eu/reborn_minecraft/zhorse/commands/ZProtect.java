package eu.reborn_minecraft.zhorse.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;

import eu.reborn_minecraft.zhorse.ZHorse;
import eu.reborn_minecraft.zhorse.enums.LocaleEnum;

public class ZProtect extends Command {

	public ZProtect(ZHorse zh, CommandSender s, String[] a) {
		super(zh, s, a);
		playerOnly = true;
		needTarget = false;
		if (isPlayer() && analyseArguments() && hasPermission() && isWorldEnabled()) {
			applyArgument(true);
			if (!idMode) {
				if (!targetMode) {
					boolean ownsHorse = ownsHorse(targetUUID, true);
					if (isOnHorse(ownsHorse)) {
						horse = (Horse)p.getVehicle();
						if (isRegistered(horse)) {
							execute();
						}
					}
					else if (ownsHorse) {
						userID = zh.getUM().getFavoriteUserID(p.getUniqueId());
						if (isRegistered(p.getUniqueId(), userID)) {
							horse = zh.getHM().getFavoriteHorse(p.getUniqueId());
							if (isHorseLoaded()) {
								execute();
							}
						}
					}
				}
				else {
					sendCommandUsage();
				}
			}
			else {
				if (isRegistered(targetUUID, userID)) {
					horse = zh.getHM().getHorse(targetUUID, userID);
					if (isHorseLoaded()) {
						execute();
					}
				}
			}
		}
	}

	private void execute() {
		if (isOwner() && zh.getEM().canAffordCommand(p, command)) {
			if (!zh.getUM().isProtected(horse)) {
				zh.getUM().protect(targetUUID, horse);
				if (displayConsole) {
					zh.getMM().sendMessageHorse(s, LocaleEnum.horseProtected, horseName);
				}
			}
			else {
				zh.getUM().unProtect(targetUUID, horse);
				if (displayConsole) {
					zh.getMM().sendMessageHorse(s, LocaleEnum.horseUnProtected, horseName);
				}
			}
			zh.getEM().payCommand(p, command);
		}
	}

}
