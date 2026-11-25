package cz.foresttech.forestredis.spigot.scheduler;

import org.bukkit.plugin.Plugin;

/**
 * サーバー実装(Bukkit/Paper または Folia)に応じてタスクをスケジュールするためのアダプター。
 */
public interface SchedulerAdapter {

    /**
     * タスクを非同期で実行します
     *
     * @param task 実行するタスク
     */
    void runAsync(Runnable task);

    /**
     * タスクを同期(Foliaの場合はグローバルリージョン)で実行します
     *
     * @param task 実行するタスク
     */
    void runSync(Runnable task);

    /**
     * 現在のサーバーがFoliaかどうかを判定します
     *
     * @return Foliaの場合はtrue
     */
    static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * 現在のサーバーに適した{@link SchedulerAdapter}を作成します
     *
     * @param plugin プラグインインスタンス
     * @return {@link SchedulerAdapter}の実装
     */
    static SchedulerAdapter create(Plugin plugin) {
        return isFolia() ? new FoliaSchedulerAdapter(plugin) : new BukkitSchedulerAdapter(plugin);
    }
}
