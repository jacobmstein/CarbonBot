package sh.stein.carbon;

/**
 * A service used to interact with Carbon.
 *
 * @see <a href="https://github.com/carbon-app/carbon">https://github.com/carbon-app/carbon</a>
 * @see <a href="https://carbon.now.sh/">https://carbon.now.sh/</a>
 */
public interface CarbonService {

    /**
     * Attempts to get an image using the specified options.
     *
     * @param options the {@code ImageOptions} instance
     * @return the image as a byte array
     */
    byte[] getImage(ImageOptions options);
}
