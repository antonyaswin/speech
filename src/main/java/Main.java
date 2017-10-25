/**
 * Created by ashu on 12/10/2017.
 */
import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.sourceforge.javaflacencoder.FLACFileWriter;


public class Main extends Canvas
{

    public void paint(Graphics g) {

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i=t.getImage("C:\\Users\\ashu\\IdeaProjects\\Version-3\\src\\main\\resources\\kevin.png");
        g.drawImage(i, 1,2, (ImageObserver) this);
    }
    public static void main(String[] args)
            throws IOException
    {
        Main m =new Main();
        Brain brain=new Brain();
        final Microphone mic = new Microphone(FLACFileWriter.FLAC);

        GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

        duplex.setLanguage("en");

        JFrame frame = new JFrame("KEVIN");
        frame.add(m);
        JTextArea response = new JTextArea();
        response.setEditable(false);
        response.setWrapStyleWord(true);
        response.setLineWrap(true);

        try {
            duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
            brain.speak("Hello  sir !! KEVINN  at your service");
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        JScrollPane scroll = new JScrollPane(response);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), 1));
        frame.getContentPane().add(scroll);
        JPanel recordBar = new JPanel();
        frame.getContentPane().add(recordBar);
        recordBar.setLayout(new BoxLayout(recordBar, 0));
        frame.setVisible(true);
        frame.pack();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        duplex.addResponseListener(new GSpeechResponseListener()
        {
            String old_text = "";

            public void onResponse(GoogleResponse gr)
            {
                String output = "";
                output = gr.getResponse();
                if (gr.getResponse() == null)
                {
                    this.old_text = response.getText();
                    if (this.old_text.contains("(")) {
                        this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
                    }
                    System.out.println("Paragraph Line Added");
                    //ApplicationConstants.set.clear();
                    ApplicationConstants.flag=true;
                    this.old_text = (response.getText() + "\n");
                    this.old_text = this.old_text.replace(")", "").replace("( ", "");
                    response.setText(this.old_text);
                    return;
                }
                if (output.contains("(")) {
                    output = output.substring(0, output.indexOf('('));
                }
                if (!gr.getOtherPossibleResponses().isEmpty()) {
                    output = output + " (" + (String)gr.getOtherPossibleResponses().get(0) + ")";
                }

                response.setText("");
                response.append(this.old_text);
                response.append(output);
                //String result=output;
                output=output.replaceAll("[(]","");
                output=output.replaceAll("[)]","");
                output=output.replaceAll("\\s","");
                //ApplicationConstants.set.add(output);
                try {
                    brain.process(output);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}

