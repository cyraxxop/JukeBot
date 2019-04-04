package jukebot.commands

import jukebot.utils.Command
import jukebot.utils.CommandProperties
import jukebot.utils.Context
import jukebot.utils.Permissions

@CommandProperties(description = "Pauses the player", aliases = ["ps"], category = CommandProperties.category.CONTROLS)
class Pause : Command(ExecutionType.REQUIRE_MUTUAL) {

    private val permissions = Permissions()

    override fun execute(context: Context) {

        val player = context.getAudioPlayer()

        if (!player.isPlaying) {
            context.embed("Not Playing", "Nothing is currently playing.")
            return
        }

        if (!permissions.ensureMutualVoiceChannel(context.member)) {
            context.embed("No Mutual VoiceChannel", "Join my VoiceChannel to use this command.")
            return
        }

        if (!context.isDJ(true)) {
            context.embed("Not a DJ", "You need to be a DJ to use this command.\n[See here on how to become a DJ](https://jukebot.serux.pro/faq)")
            return
        }

        player.player.isPaused = true

    }

}