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

public class Connect implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      if (args.length == 0) {
        sender.sendMessage(ChatColor.RED + "Usage: /connect <Server> [Player]");
        return true;
      }

      if (args.length == 1) {
        if (sender.hasPermission("bungeecord.server" + args[0].toLowerCase()) || sender.isOp()) {
          ByteArrayDataOutput out = ByteStreams.newDataOutput();
          out.writeUTF("Connect");
          out.writeUTF(args[0]);
          byte[] data = out.toByteArray();

          Plugin plugin = Bukkit.getPluginManager().getPlugin("Ktrnds_Transport");
          player.sendPluginMessage(Objects.requireNonNull(plugin), "BungeeCord", data);

          return true;
        } else {
          sender.sendMessage(ChatColor.RED + "You don't have permission to access this server");
        }
      } else {
        if (sender.hasPermission("ktrnds.transport.connect.other") || sender.isOp()) {
          Player target = Bukkit.getPlayer(args[1]);
          if (target != null) {
            if (target.hasPermission("bungeecord.server" + args[0].toLowerCase()) || target.isOp()) {
              ByteArrayDataOutput out = ByteStreams.newDataOutput();
              out.writeUTF("Connect");
              out.writeUTF(args[0]);
              byte[] data = out.toByteArray();

              Plugin plugin = Bukkit.getPluginManager().getPlugin("Ktrnds_Transport");
              target.sendPluginMessage(Objects.requireNonNull(plugin), "BungeeCord", data);

              return true;
            } else {
              sender.sendMessage(ChatColor.RED + "User doesn't have permission to access this server");
            }
          } else {
            sender.sendMessage(ChatColor.RED + "Player not found");
            return true;
          }
        } else {
          sender.sendMessage(ChatColor.RED + "You don't have permission to use this command");
          return true;
        }
      }
    } else {
      sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
    }
    return true;
  }
}
