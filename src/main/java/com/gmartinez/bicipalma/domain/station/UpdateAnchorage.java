package com.gmartinez.bicipalma.domain.station;

import com.gmartinez.bicipalma.domain.station.Anchorage.AnchorageBuilder;

public interface UpdateAnchorage<ObjectAnchored> {

	AnchorageBuilder<ObjectAnchored> toBuilder();
}
