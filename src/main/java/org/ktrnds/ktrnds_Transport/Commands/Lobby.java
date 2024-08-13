package org.ktrnds.ktrnds_Transport.Commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class Lobby implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      try {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("lobby");
        byte[] data = out.toByteArray();

        Plugin plugin = Bukkit.getPluginManager().getPlugin("Ktrnds_Transport");
        player.sendPluginMessage(Objects.requireNonNull(plugin), "BungeeCord", data);

        return true;
      } catch (Exception ignored) {
        return true;
      }
    } else {
      sender.sendMessage(ChatColor.RED + "このコマンドはゲーム内でのみ実行可能です");
      return true;
    }
  }
}
