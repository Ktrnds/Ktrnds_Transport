package org.ktrnds.ktrnds_Transport;

import org.bukkit.plugin.java.JavaPlugin;
import org.ktrnds.ktrnds_Transport.Commands.Connect;

import java.util.Objects;

public final class Ktrnds_Transport extends JavaPlugin {

  @Override
  public void onEnable() {
    // Plugin startup logic

    this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

    Objects.requireNonNull(this.getCommand("connect")).setExecutor(new Connect());
    Objects.requireNonNull(this.getCommand("lobby")).setExecutor(new Connect());
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
