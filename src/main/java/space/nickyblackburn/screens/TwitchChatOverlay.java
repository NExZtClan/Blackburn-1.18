

/***
 * this class is for setting up twtich chat iirc in minecraft so we can see the messages from the irrc chat without an overlay
 */
package space.nickyblackburn.screens;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Strings;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.entity.Entity;
import space.nickyblackburn.utils.Consts;
import net.optifine.util.*;

public class TwitchChatOverlay extends GuiComponent{
    
    Minecraft minecraft;
    Font font;
    List <String> chat = new LinkedList();

    private long updateChatMS = 0L;
    // wats called when wana init the overlay 
    public TwitchChatOverlay(Minecraft mc){
        this.minecraft = mc;
        this.font = mc.font;
        
    }
    // renders the twitch chat 
    public void render(PoseStack pPoseStack){

        Entity entity = this.minecraft.getCameraEntity();
        drawTwitchChat(pPoseStack);

       
    }

    // draws the twtich chat on the overlay 
    //TODO: actually get twitch chat working 
    protected void drawTwitchChat(PoseStack pose){

        GuiPoint[] aguipoint = new GuiPoint[ Consts.chatmessage.size()];
        GuiRect[] aguirect = new GuiRect[ Consts.chatmessage.size()];

    
      
        for (int i = 0; i < Consts.chatmessage.size(); ++i)
        {
            
            String s =  (String) Consts.chatmessage.get(i);

            if (!Strings.isNullOrEmpty(s))
            {
                int j = 9;
                int k = this.font.width(s);
                int l = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                int i1 = 2 + j * i;
                aguirect[i] = new GuiRect(l - 1, i1 - 1, l + k + 1, i1 + j - 1);
                aguipoint[i] = new GuiPoint(l, i1);

                
            }

            if(Consts.chatmessage.size() == 11){
                pose.translate(-4.0D, 0.0D, 0.0D);
                Consts.chatmessage.clear();
            }

        }
        

        GuiUtils.fill(pose.last().pose(), aguirect, -1873784752);
        this.font.renderStrings(Consts.chatmessage, aguipoint, 14737632, pose.last().pose(), false, this.font.isBidirectional());
    
        
    }
}
