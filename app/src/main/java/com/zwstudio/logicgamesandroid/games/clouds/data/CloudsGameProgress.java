package com.zwstudio.logicgamesandroid.games.clouds.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zwstudio.logicgamesandroid.common.data.GameProgress;

/**
 * Created by zwvista on 2016/09/29.
 */

@DatabaseTable
public class CloudsGameProgress extends GameProgress {
    @DatabaseField
    public int markerOption;
}