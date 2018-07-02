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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Mod root class, with Mod.EventHandler logic
 */
@SuppressWarnings("unused,WeakerAccess")
@Mod(modid = ThirdPersonMod.MODID, version = ThirdPersonMod.VERSION, name = ThirdPersonMod.NAME,
        dependencies = "required-after:forge@[14.23.4.2705,)",
        guiFactory = "com.ezrol.terry.minecraft.wastelands.client.GuiFactory")
public class ThirdPersonMod {
    public static final String MODID = "thirdpersonmod";
    public static final String VERSION = "${version}";
    public static final String NAME = "Third Person Mod";

    @SidedProxy(clientSide = "com.ezrol.terry.minecraft.thirdperson.ClientProxy",
            serverSide = "com.ezrol.terry.minecraft.thirdperson.ServerProxy")
    private static CommonProxy proxy;

    /**
     * Give the log to our proxy
     *
     * @param event pre-init event
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.setLogger(event.getModLog());
    }

    /**
     * Call the proxy init
     *
     * @param event init event
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
