package cz.foresttech.forestredis.spigot.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Folia用の{@link SchedulerAdapter}実装。
 * <p>
 * このクラスはFolia上でのみロードされます。
 */
public class FoliaSchedulerAdapter implements SchedulerAdapter {

    private final Plugin plugin;

    public FoliaSchedulerAdapter(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void runAsync(Runnable task) {
        Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> task.run());
    }

    @Override
    public void runSync(Runnable task) {
        Bukkit.getGlobalRegionScheduler().execute(plugin, task);
    }
}
