package config;

import model.mail.Group;
import java.util.List;

/***
 * This interface is used to perform operations on the config files.
 * The implementation of the interface decides how the data should be accessed.
 *
 * This interface need :
 * -    a smtp server
 * -    a listening port on the server
 * -    a number of groups
 * -    a Group of witnesses
 * -    a Group of victims
 * -    a list of prank messages
 *
 * @author Loïc Frueh, Dejvid Muaremi
 */
public interface IConfigManager {
    
    /***
     * When this method is invoked it will get the smpt server address.
     * @return the smtp server address
     */
    public String getSmtpServerAddress();
    
    /***
     * When this method is invoked it will get the smtp server listening port.
     * @return the smtp server listening port.
     */
    public int getSmtpServerListenPort();
    
    /***
     * When this method is invoked it will get the number of Groups that we need.
     * @return the number of Groups needed.
     */
    public int getNumberOfGroups();
    
    /***
     * When this method is invoked it will get a Group of witnesses.
     * They will receive the fake email to check them.
     * @return a Group of witnesses.
     */
    public Group getWitnesses();
    
    /***
     * When this method is invoked it will get a Group of victims.
     * They will receive the fake email and believe they are real.
     * @return a Group of victims.
     */
    public Group getVictims();
    
    /***
     * When this method is invoked it will get a list of pranks.
     * These pranks will be sent to the victims and the witnesses.
     * @return a list of messages.
     */
    public List<String> getMessages();

}
