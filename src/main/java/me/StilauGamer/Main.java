package me.StilauGamer;

import co.aikar.commands.PaperCommandManager;
import me.StilauGamer.Listener.chatFormatListener;
import me.StilauGamer.commands.inGameCommands.ConfigGuiCommand;
import me.StilauGamer.commands.inGameCommands.gamemodeCommand;
import me.StilauGamer.events.eventMessages;
import me.StilauGamer.events.eventPlayerSneak;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public final class Main extends JavaPlugin {

    // private
    private static JDA jda;
    private static Chat chat = null;
    private static PaperCommandManager manager;
    private ConfigManager cfgm;

    private Connection connection;
    private String host, database, username, password;
    private int port;

    // public
    public static JDA getJda() { return jda; }
    public static Chat getChat() { return chat; }
    public static PaperCommandManager getManager() { return manager; }


    // Strings
    public static String prefix = "!";


    @Override
    public void onEnable() {

        // Database
        host = "5.189.143.167";
        port = 3306;
        database = "stilaugamer_login";
        username = "stilaugamer_stilaugamer";
        password = "StilauGamer3212";

        try {
            openConnection();
            System.out.println("Connected!");
        } catch (SQLException x) {
            x.printStackTrace();
        }
        // ----------------------------
        //
        // ACF
        manager = new PaperCommandManager(this);
        registerCommands(manager);
        // ----------------------------
        //
        // Listeners
        Bukkit.getPluginManager().registerEvents(new eventMessages(this), this);
        Bukkit.getPluginManager().registerEvents(new chatFormatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new eventPlayerSneak(), this);
        // ----------------------------
        //
        // Discord
        try {
            // Starting the bot!
            jda = JDABuilder.create(getConfig().getString("discord.token"), Arrays.asList(GatewayIntent.values()))
                    .enableCache(CacheFlag.MEMBER_OVERRIDES)
                    .build()
                    .awaitReady();

            // Presence
            jda.getPresence().setActivity(Activity.watching("Philip on toilet!"));

        } catch (InterruptedException | LoginException x) {

            // Remove Invalid Token Error!
            if (x.toString().startsWith("javax.security.auth.login.LoginException: The provided token is invalid!")) {
                System.out.println(ChatColor.RED + "Invalid Token! Please insert a discord bot token in the config.yml to use the discord intergration feature!");
            }

            // Print Stack Trace
            // x.printStackTrace();

        }
        // ----------------------------
        //
        // Checking for Plugins!
        // Vault
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {

            // Message sent to console!
            System.out.println(ChatColor.GREEN + "Successfully found Vault!");

        } else {

            // Message sent when server couldn't find Vault!
            throw new RuntimeException(ChatColor.RED + "Could not find Vault! Please install it before you use the plugin!");

        }


        // ----------------------------
        //
        // LoadConfig
        loadConfigs();
        // ----------------------------
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // Vault Chat Implementing
    public boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
    // ----------------------------

    // Config
    public void loadConfigs() {
        cfgm = new ConfigManager();

        // Config Setup
        cfgm.setupData();
        cfgm.setupMessages();

        // Config Get
        cfgm.getData().options().copyDefaults(true);
        cfgm.getMessages().options().copyDefaults(true);

        getConfig().options().copyDefaults(true);

        // Config Messages!
        cfgm.Messages();

        //Config Save!
        cfgm.saveData();
        cfgm.saveMessages();

        saveDefaultConfig();

    }
    // ----------------------------

    public void registerCommands(PaperCommandManager manager) {

        // Enable the help API!
        manager.enableUnstableAPI("help");

        // Register tab-autocomplete options.
        manager.getCommandCompletions().registerAsyncCompletion("configgui", context ->
                Arrays.asList("cg")
        );

        // Registering my Commands!
        manager.registerCommand(new ConfigGuiCommand(this));
        manager.registerCommand(new gamemodeCommand());

        // Default Exception Handler!
        manager.setDefaultExceptionHandler((command, registeredCommand, sender, args, t) -> {

            // Sending the Warning!
            getLogger().warning("Error occured while executing command: " + command.getName());

            // Returning it false!
            return false;
        });

    }

    private void openConnection() throws SQLException {

        if (connection != null && !connection.isClosed()) {
            return;
        }

        connection = DriverManager.getConnection("jdbc:mysql://" +
                this.host + ":" +
                this.port + "/" +
                this.database,
                this.username,
                this.password);

    }

}
