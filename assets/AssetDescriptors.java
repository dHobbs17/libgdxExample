package com.mygdx.game.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

public class AssetDescriptors {
    public static final AssetDescriptor<Texture> hero = new AssetDescriptor<Texture>(Assets.HERO, Texture.class);
    public static final AssetDescriptor<Texture> villan = new AssetDescriptor<Texture>(Assets.VILLAN, Texture.class);
    public static final AssetDescriptor<Texture> floor = new AssetDescriptor<Texture>(Assets.FLOOR, Texture.class);
    public static final AssetDescriptor<Texture> grid = new AssetDescriptor<Texture>(Assets.GRID, Texture.class);
    public static final AssetDescriptor<Texture> bucket = new AssetDescriptor<Texture>(Assets.BUCKET, Texture.class);
    public static final AssetDescriptor<Texture> bullet = new AssetDescriptor<Texture>(Assets.BULLET, Texture.class);

    private AssetDescriptors(){}
}