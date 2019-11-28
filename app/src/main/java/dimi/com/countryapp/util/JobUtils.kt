package dimi.com.countryapp.util

import android.app.job.JobScheduler
import androidx.core.content.ContextCompat.getSystemService
import android.app.job.JobInfo
import android.content.ComponentName
import android.content.Context


fun scheduleJob(context: Context) {
    val serviceComponent = ComponentName(context, TestJobService::class.java)
    val builder = JobInfo.Builder(0, serviceComponent)
    builder.setMinimumLatency((1 * 1000).toLong()) // wait at least
    builder.setOverrideDeadline((3 * 1000).toLong()) // maximum delay
    //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
    //builder.setRequiresDeviceIdle(true); // device should be idle
    //builder.setRequiresCharging(false); // we don't care if the device is charging or not
    val jobScheduler = context.getSystemService(JobScheduler::class.java)
    jobScheduler.schedule(builder.build())
}