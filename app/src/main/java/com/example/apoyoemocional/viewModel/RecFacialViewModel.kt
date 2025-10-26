import android.hardware.camera2.params.Face
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecFacialViewModel : ViewModel() {
    val _faces = MutableStateFlow<List<Face>>(emptyList())
    val faces: StateFlow<List<Face>> = _faces

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val detectorOptions = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
        .build()


    private val detector = FaceDetection.getClient(detectorOptions)

    fun processImage(image: InputImage) {
        detector.process(image)
            .addOnSuccessListener { detectedFaces ->
                _faces.value = detectedFaces as List<Face>
                _error.value = null
            }
            .addOnFailureListener { e ->
                _faces.value = emptyList()
                _error.value = e.message
            }
    }


}
