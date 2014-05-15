package testPlug;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin; 

public final class testPlug extends JavaPlugin implements Listener {

	private ArrayList<Double> blocks;
	
	public void onEnable()
	{
		getLogger().info("onEnable has been invoked!");
	}
	
	public void onDisable()
	{
		getLogger().info("onDisable has been invoked!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("testplugin"))
		{
			if (!(sender instanceof Player))
			{
				sender.sendMessage("Only players can use this command.");
			}
			else
			{
				testPlugin(sender);
				return true;
			}
		}
		return false;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		player.setFlying(true);
		Bukkit.broadcastMessage("Welcome " + player.getName() + "!");
	}
	
	public void testPlugin(CommandSender sender)
	{
		Player p = (Player) sender;
		Location l = p.getLocation();
		l.setY(l.getBlockY()+ 3);
		Block block = l.getBlock();
		block.setType(Material.STONE);
		blocks.add(l.getX());
		l.setY(l.getY() + 2);
		for (int i = 0; i < 5; i++)
		{		
			blocks.add(l.getX());
			l.setY(l.getY() + 1);
			block = l.getBlock();
			block.setType(Material.STONE);
		}
		for (int  i = 0; i < 5; i++)
		{
			l.setX(blocks.get(i) + 1);
			block = l.getBlock();
			block.setType(Material.STONE);
		}
	}
}