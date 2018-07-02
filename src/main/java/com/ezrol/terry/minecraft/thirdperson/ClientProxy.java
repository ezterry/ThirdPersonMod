/*
 * Copyright (c) 2018. Terrence Ezrol (ezterry)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * + Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * + Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.ezrol.terry.minecraft.thirdperson;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.apache.logging.log4j.Level;

/**
 * ClientProxy, implements the events used by the client
 * For ThirdPersonMod this subscribes to ClientConnectedToServerEvent
 * where we override the ingameGUI with our own instance that
 * ensures we are never in first person view
 */
@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy{
    /**
     * Basic constructor
     */
    public ClientProxy(){
    }

    /**
     * The init event, register to the EventBus
     *
     * @param event mod init event
     */
    @Override
    public void init(FMLInitializationEvent event) {
        mclogger.log(Level.INFO,"install custom event on server start");
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * The ClientConnectedToServerEvent, inject the custom gui instance
     *
     * @param event the server event instance
     */
    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onServerConnection(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        Minecraft mc = Minecraft.getMinecraft();

        //noinspection ConstantConditions
        if(mc == null){
            mclogger.error("Unable to find Minecraft instance");
        }
        else{
            mclogger.info("Attempting to inject custom GUI");
            mc.ingameGUI = new CustomInGameGui(mc);
            mclogger.info("GUI Injected");
        }
    }
}
