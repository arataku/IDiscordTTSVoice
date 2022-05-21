package dev.felnull.ttsvoice.tts;

public interface IVoiceType {
    String getTitle();

    String getId();

    byte[] getSound(String text) throws Exception;
}