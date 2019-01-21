package com.ripstech.api.utils.issue

import com.ripstech.api.connector.Phase

data class Progress(val percent: Int,  val phase: Phase) {

	fun isScanFinished() = percent == 100 && phase == Phase.DONE

	override fun toString(): String {
		return "%3d%% - %s".format(percent, phase.description)
	}

}