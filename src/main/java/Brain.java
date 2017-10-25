import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.synthesiser.SynthesiserV2;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import net.sourceforge.javaflacencoder.FLACFileWriter;

import java.io.IOException;
import java.util.Set;

/**
 * Created by ashu on 13/10/2017.
 */
public class Brain {
    VoiceManager voiceManager;
    Voice voice;
    final Microphone mic = new Microphone(FLACFileWriter.FLAC);
    SynthesiserV2 synthesiser=new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    public void process(String value) throws IOException {
            value=value.trim();

            System.out.println(value);

            if ((value.toLowerCase().equalsIgnoreCase(Commands.CHROME.toLowerCase()))&&ApplicationConstants.flag) {
                try {
                    speak("sure ! sir");
                    ApplicationConstants.flag=false;
                    Runtime.getRuntime().exec(ApplicationConstants.FOLDERACTION + ApplicationConstants.CHROME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ((value.toLowerCase().equalsIgnoreCase(Commands.PLAYMUSIC.toLowerCase()))&&ApplicationConstants.flag) {
                try {
                    speak("sure ! sir");
                    ApplicationConstants.flag=false;
                    Runtime.getRuntime().exec(ApplicationConstants.FOLDERACTION + ApplicationConstants.MUSICPATH);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if ((value.toLowerCase().equalsIgnoreCase(Commands.MSWORD.toLowerCase()))&&ApplicationConstants.flag) {
                try {
                    speak("sure ! sir");
                    ApplicationConstants.flag=false;
                    Runtime.getRuntime().exec(ApplicationConstants.FOLDERACTION + ApplicationConstants.MSWORDPATH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (value.toLowerCase().equalsIgnoreCase(Commands.MSPOWERPOINT.toLowerCase())&&ApplicationConstants.flag) {
                try {
                    speak("sure ! sir");
                    ApplicationConstants.flag=false;
                    Runtime.getRuntime().exec(ApplicationConstants.FOLDERACTION + ApplicationConstants.MSPOWERPOINTPATH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ((value.toLowerCase().equalsIgnoreCase(Commands.MSEXCEL.toLowerCase()))&&ApplicationConstants.flag) {
                try {
                    speak("sure ! sir");
                    ApplicationConstants.flag=false;
                    Runtime.getRuntime().exec(ApplicationConstants.FOLDERACTION + ApplicationConstants.MSEXCEL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ((value.toLowerCase().equalsIgnoreCase(Commands.SLEEP.toLowerCase()))&&ApplicationConstants.flag) {
                mic.close();
                try {
                    speak("see you soon sir!");
                    ApplicationConstants.flag=false;
                    Thread.sleep(1900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);

            }
        }


    public void speak(String text) {
        /*System.out.println("Inside speak");
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Inside synthesiser");
                Player player = new Player(synthesiser.getMP3Data(text));
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(false);
        thread.start();*/
        System.setProperty("mbrola.base","D:/mbrola");
        voiceManager=VoiceManager.getInstance();
        voice=voiceManager.getVoice("kevin16");
        voice.allocate();
        voice.speak(text);
        voice.deallocate();
    }

}
