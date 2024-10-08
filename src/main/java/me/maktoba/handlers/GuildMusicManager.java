package me.maktoba.handlers;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.entities.Guild;

public class GuildMusicManager {

    private TrackScheduler trackScheduler;
    private MusicHandler musicHandler;

    public GuildMusicManager(AudioPlayerManager manager, Guild guild) {
        AudioPlayer player = manager.createPlayer();
        trackScheduler = new TrackScheduler(player);
        player.addListener(trackScheduler);
        musicHandler = new MusicHandler(player);
    }

    public TrackScheduler getTrackScheduler() {
        return trackScheduler;
    }

    public MusicHandler getAudioForwarder() {
        return musicHandler;
    }
}